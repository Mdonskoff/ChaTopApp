package com.chatop.ChaTopApp.service;

import com.chatop.ChaTopApp.dto.RentalsDto;
import com.chatop.ChaTopApp.model.Rentals;
import com.chatop.ChaTopApp.model.Users;
import com.chatop.ChaTopApp.repository.RentalsRepository;
import com.chatop.ChaTopApp.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class RentalsService {

    @Autowired
    private RentalsRepository rentalsRepository;
    @Autowired
    private UsersRepository usersRepository;

    public Rentals createRental(HashMap<String, String> rentalInfo, MultipartFile picture) {
        Rentals newRental = createRentalWithData(rentalInfo);
        if (newRental == null) {
            return null;
        }
        newRental.setPicture(uploadImage(picture));
        return rentalsRepository.save(newRental);
    }

    public Rentals createRentalWithData(HashMap<String, String> rentalInfo) {
        // à modifier pour la sécurité
        Rentals rental = new Rentals();
        try {
            if(rentalInfo.containsKey("surface")) {
                rental.setSurface(Float.parseFloat(rentalInfo.get("surface")));
            }
            if(rentalInfo.containsKey("price")) {
                rental.setPrice(Float.parseFloat(rentalInfo.get("price")));
            }
            if(rentalInfo.containsKey("description")) {
                rental.setDescription(rentalInfo.get("description"));
            }
            if(rentalInfo.containsKey("name")) {
                rental.setName(rentalInfo.get("name"));
            }
            // à modifier après la sécurité, obtenir le user connecté
            Optional<Users> user = usersRepository.findById(1);
            rental.setOwner(user.get());
            return rental;

        }catch (Exception ex) {
            log.error("[createRentalWithData] ", ex.getMessage());
            return null;
        }
    }

    // Upload an image and return a new image URL
    public String uploadImage(MultipartFile picture) {
        try {
            Dotenv dotenv = Dotenv.load();
            Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
            cloudinary.config.secure = true;

            Map res = cloudinary.uploader().upload(picture.getBytes(), ObjectUtils.emptyMap());
            return (String) res.get("url");
        } catch(Exception ex) {
            log.error("Upload image " + ex.getMessage());
        }
        return "https://upload.wikimedia.org/wikipedia/commons/a/a3/Image-not-found.png";
    }

    public RentalsDto getARentalDto(int idRental) {
        try {
            Rentals rental;
            rental = rentalsRepository.findById(idRental).get();

            RentalsDto dto = convertRentalsToRentalsDto(rental);
            return dto;
        } catch(Exception e) {
            log.error(e.getMessage()+ "Erreur dans getRental");
            return null;
        }
    }

    public Rentals getARental(int idRental) {
        return rentalsRepository.findById(idRental).get();
    }
    public RentalsDto updateARental(int idRental, HashMap<String, String> infoRental) {
        Rentals rental = getARental(idRental);
        if (rental == null) {
            return null;
        }
        if (infoRental.containsKey("name")) {
            rental.setName(infoRental.get("name"));
        }
        if (infoRental.containsKey("surface")) {
            rental.setSurface(Float.parseFloat(infoRental.get("surface")));
        }
        if (infoRental.containsKey("price")) {
            rental.setPrice(Float.parseFloat(infoRental.get("price")));
        }
        if (infoRental.containsKey("description")) {
            rental.setDescription(infoRental.get("description"));
        }
        Rentals updatedRental = rentalsRepository.save(rental);
        RentalsDto dto = convertRentalsToRentalsDto(updatedRental);
        return dto;
    }

    public List<RentalsDto> getAllRentals() {
        List<Rentals> rentalsList = rentalsRepository.findAll();
        List<RentalsDto> rentalsDtoList = new ArrayList<>();
        for (Rentals rental : rentalsList ) {
            rentalsDtoList.add(convertRentalsToRentalsDto(rental));
        }
        return rentalsDtoList;
    }

    public RentalsDto convertRentalsToRentalsDto(Rentals rental) {
        if (rental == null) {
            return null;
        }
        RentalsDto dto = new RentalsDto();
        dto.setName(rental.getName());
        dto.setId(rental.getId());
        dto.setDescription(rental.getDescription());
        dto.setPicture(rental.getPicture());
        dto.setPrice(rental.getPrice());
        dto.setSurface(rental.getSurface());
        dto.setOwner_id(rental.getOwner().getId());
        dto.setCreated_at(rental.getCreated_at());
        dto.setUpdated_at(rental.getUpdated_at());
        return dto;
    }

}

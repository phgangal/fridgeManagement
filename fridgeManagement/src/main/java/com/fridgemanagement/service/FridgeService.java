package com.fridgemanagement.service;

import com.fridgemanagement.exception.FridgeManagementException;
import com.fridgemanagement.exception.FridgeNotFoundException;
import com.fridgemanagement.model.Fridge;
import com.fridgemanagement.repository.FridgeRepository;
import com.fridgemanagement.utils.FridgeManagementConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CleanupFailureDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FridgeService {
    @Autowired
    FridgeRepository fridgeRepository;
    @Autowired
    RestOperations restOperations;


    /**
     * This method retrieves Fridge object by passing in the fridgeId
     *
     * @param fridgeId
     * @return Fridge
     * @throws FridgeManagementException
     */
    public Fridge retrieveFridge(Integer fridgeId) throws FridgeManagementException {
        Optional<Fridge> fridgeDetails = null;
        try {
            fridgeDetails = fridgeRepository.findById(fridgeId);
        } catch (CleanupFailureDataAccessException exp) {
            throw new FridgeNotFoundException("Unable to retrieve fridge information", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return fridgeDetails.get();
    }


    /**
     * This object retrieves all the soda present in the fridge.
     *
     * @param fridgeId
     * @return // List<sodaId></>
     * @throws FridgeManagementException
     */
    public List<Integer> retrieveSodasInFridge(Integer fridgeId) throws FridgeManagementException {
        List<Integer> sodaList = new ArrayList<Integer>();
        try {
            Fridge fridgeDetail = fridgeRepository.findById(fridgeId).get();
            sodaList = fridgeDetail.getSodaList();
        } catch (CleanupFailureDataAccessException exp) {
            throw new FridgeNotFoundException("Unable to retrieve fridge information", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return sodaList;
    }


    /**
     * Saves fridge to fridge table
     *
     * @param fridge
     * @return void
     * @throws FridgeManagementException
     */
    public Fridge saveFridge(Fridge fridge) throws FridgeManagementException {
        Fridge fridgeInst = null;
        try {
            fridgeInst = fridgeRepository.save(fridge);
        } catch (CleanupFailureDataAccessException exp) {
            throw new FridgeManagementException("Could add new fridge ", HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return fridgeInst;
    }


    /**
     * Deletes a fridge from fridge table.
     *
     * @param fridge
     * @throws FridgeManagementException
     */
    public void deleteFridge(Fridge fridge) throws FridgeManagementException {
        try {
            fridgeRepository.delete(fridge);
        } catch (CleanupFailureDataAccessException exp) {
            throw new FridgeManagementException("Could not delete fridge", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a soda from the fridge from fridge table.
     *
     * @param fridge
     * @throws FridgeManagementException
     */
    public Fridge deleteSodaFromFridge(Fridge fridge, Integer sodaId) throws FridgeManagementException {
        Fridge fridgeInst = null;
        try {
            if ((!fridge.equals(null)) || fridge.getSodaList().size() > 0) {
                fridge.getSodaList().remove(sodaId);
                fridgeInst = fridgeRepository.save(fridge);
            } else {
                throw new FridgeManagementException("Could not delete soda from fridge", HttpStatus.BAD_REQUEST);
            }
        } catch (CleanupFailureDataAccessException exp) {
            throw new FridgeManagementException("Could not delete soda from fridge", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return fridgeInst;
    }

    /**
     * Adds a soda from the fridge from fridge table.
     *
     * @param fridge
     * @throws FridgeManagementException
     */
    public Fridge addeSodaToFridge(Fridge fridge, Integer sodaId) throws FridgeManagementException {
        Fridge fridgeInst = null;
        try {
            if (fridge.getSodaList().size() < FridgeManagementConstant.MAX_SODA_IN_FRIDGE) {
                fridge.getSodaList().add(sodaId);
                fridgeInst = fridgeRepository.save(fridge);
            } else {
                throw new FridgeManagementException("Could not add soda to fridge", HttpStatus.BAD_REQUEST);
            }
        } catch (CleanupFailureDataAccessException exp) {
            throw new FridgeManagementException("Could not add soda to fridge", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return fridgeInst;
    }
}
package com.fridgemanagement.service;

import com.fridgemanagement.exception.FridgeManagementException;
import com.fridgemanagement.exception.SodaNotFoundException;
import com.fridgemanagement.model.Soda;
import com.fridgemanagement.repository.FridgeRepository;
import com.fridgemanagement.repository.SodaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CleanupFailureDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.Optional;

@Service
public class SodaService {

    @Autowired
    SodaRepository sodaRepository;
    @Autowired
    FridgeRepository fridgeRepository;
    @Autowired
    RestOperations restOperations;

    /**
     * Finds a soda from the soda table.
     *
     * @param sodaId
     * @return
     * @throws SodaNotFoundException
     */
    public Soda retrieveSoda(Integer sodaId) throws SodaNotFoundException {
        Optional<Soda> sodaDetails = null;
        try {
            sodaDetails = sodaRepository.findById(sodaId);
        } catch (CleanupFailureDataAccessException exp) {
            throw new SodaNotFoundException("Unable to find soda", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return sodaDetails.get();
    }


    /**
     * Saves a soda to soda table
     *
     * @param soda
     * @throws FridgeManagementException
     */
    public Soda saveSoda(Soda soda) throws FridgeManagementException {
        try {
           return sodaRepository.save(soda);
        } catch (CleanupFailureDataAccessException exp) {
            throw new FridgeManagementException("Unable to save soda", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Deletes a soda from stock - soda table
     *
     * @param soda
     * @throws FridgeManagementException
     */
    public void deleteSoda(Soda soda) throws FridgeManagementException {
        try {
            sodaRepository.delete(soda);
        } catch (CleanupFailureDataAccessException exp) {
            throw new FridgeManagementException("Unable to delete soda", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * This method returs all the sodas associated with a fridge.
     *
     * @param sodaIdsOfFridge
     * @return Iterable<Soda></>
     * @throws SodaNotFoundException
     */
    public Iterable<Soda> getAllSodasForAFridge(Iterable<Integer> sodaIdsOfFridge) throws SodaNotFoundException {
        Iterable<Soda> iterableFridgeSodaDetails = null;
        try {
            iterableFridgeSodaDetails = sodaRepository.findAllById(sodaIdsOfFridge);
        } catch (CleanupFailureDataAccessException exp) {
            throw new SodaNotFoundException("Unable to find sodas", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return iterableFridgeSodaDetails;
    }

}

package com.fridgemanagement.controller;

import com.fridgemanagement.exception.FridgeManagementException;
import com.fridgemanagement.exception.FridgeManagementExceptionHandler;
import com.fridgemanagement.model.Fridge;
import com.fridgemanagement.model.Soda;
import com.fridgemanagement.service.FridgeService;
import com.fridgemanagement.service.SodaService;
import com.fridgemanagement.service.UserService;
import com.fridgemanagement.utils.FridgeMgmtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Fridge Controller, all the fridge related api's
 */
@RestController
public class FridgeSodaController extends FridgeManagementExceptionHandler {

    @Autowired
    FridgeService fridgeService;
    @Autowired
    SodaService sodaService;
    @Autowired
    UserService userService;

    /**
     * This method gets the specific soda from the fridge
     *
     * @param sodaId
     * @return soda
     * @throws FridgeManagementException
     */
    @GetMapping(value = "v1/soda/{sodaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Soda retriveSodaBySodaId(@PathVariable Integer sodaId) throws FridgeManagementException {
        Soda soda = sodaService.retrieveSoda(sodaId);
        return soda;
    }

    /**
     * This method saves soda to soda table
     *
     * @param soda
     * @return void
     * @throws FridgeManagementException
     */
    /*

     /**
     * This method saves/updates a soda in soda table
     */
    @PutMapping(value = "v1/soda/{sodaId}", consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    public Soda saveSoda(@PathVariable Integer sodaId, @RequestBody Soda soda) throws FridgeManagementException {
        return sodaService.saveSoda(soda);
    }

    /**
     * This method saves/updates a soda in soda table
     */
    @PostMapping(value = "v1/soda/{sodaId}", consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    public Soda saveNewSoda(@PathVariable Integer sodaId, @RequestBody Soda soda) throws FridgeManagementException {
        Soda sodaInst = null;
        try{
            sodaInst = sodaService.saveSoda(soda);
        } catch (FridgeManagementException exp) {
            sodaInst = sodaService.saveSoda(soda);
        }
        return sodaInst;
    }

    /**
     * This method deletes soda to soda table
     *
     * @param soda
     * @return void
     * @throws FridgeManagementException
     */
    @DeleteMapping(value = "v1/soda/{sodaId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSoda(@PathVariable Integer sodaId,  @RequestBody Soda soda) throws FridgeManagementException {
        sodaService.deleteSoda(soda);
    }

    /**
     * This method gets all the sodas(sodaIds) from the fridge
     *
     * @param fridgeId
     * @return Fridge
     * @throws FridgeManagementException
     */
    @GetMapping(value = "v1/fridge/{fridgeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Fridge retriveFridgeDetailsByFridgeId(@PathVariable Integer fridgeId) throws FridgeManagementException {
        Fridge fridge = fridgeService.retrieveFridge(fridgeId);
        return fridge;
    }

    /**
     * This method gets all the products(sodas from the fridge)
     *
     * @param fridge
     * @return void
     * @throws FridgeManagementException
     */
    @PutMapping(value = "v1/fridge/{fridgeId}", consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    public Fridge saveFridge(@PathVariable Integer fridgeId, @RequestBody Fridge fridge) throws FridgeManagementException {
        return fridgeService.saveFridge(fridge);
    }

    /**
     * This method saves/updates a soda in soda table
     */
    @PostMapping(value = "v1/fridge/{fridgeId}", consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    public Fridge saveNewFridge(@PathVariable Integer fridgeId, @RequestBody Fridge fridge) throws FridgeManagementException {
        Fridge fridgeInst = null;
        try{
            fridgeInst = fridgeService.retrieveFridge(fridgeId);
        } catch (FridgeManagementException exp) {
            fridgeInst = fridgeService.saveFridge(fridge);
        }
        return fridgeInst;
    }

    /**
     * This method gets all the products(sodas from the fridge)
     *
     * @param fridge
     * @return void
     * @throws FridgeManagementException
     */
    @DeleteMapping(value = "v1/fridge/{fridgeId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteFridge(@PathVariable Integer fridgeId, @RequestBody Fridge fridge) throws FridgeManagementException {
        fridgeService.deleteFridge(fridge);
    }

    /**
     * This method gets all the sodas from the fridge
     *
     * @param fridgeId
     * @return soda
     * @throws FridgeManagementException
     */
    @GetMapping(value = "v1/fridge/{fridgeId}/soda", consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Soda> retriveSodasForFridge(@PathVariable Integer fridgeId) throws FridgeManagementException {
        Fridge fridge = fridgeService.retrieveFridge(fridgeId);
        Iterable<Soda> iterableSodas = sodaService.getAllSodasForAFridge(fridge.getSodaList());
        List<Soda> sodas = (List<Soda>) FridgeMgmtUtil.getCollectionFromIteralbe(iterableSodas);
        return sodas;
    }


    /**
     * This method adds soda to fridge
     *
     * @param fridge
     * @param sodaId
     * @return void
     * @throws FridgeManagementException
     */
    @PutMapping(value = "v1/fridge/{sodaId}", consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    public Fridge addSodaToFridge(@PathVariable Integer sodaId, @RequestBody Fridge fridge) throws FridgeManagementException {
        return fridgeService.addeSodaToFridge(fridge, sodaId);
    }

    /**
     * This method delets soda from fridge
     *
     * @param fridgeId
     * @param sodaId
     * @return void
     * @throws FridgeManagementException
     */
    @PostMapping(value = "v1/fridge/{fridgeId}/soda/{sodaId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Fridge deleteSodaFromFridge(@PathVariable Integer fridgeId, @PathVariable Integer sodaId, @RequestBody Fridge fridge) throws FridgeManagementException {
        return fridgeService.deleteSodaFromFridge(fridge, sodaId);
    }

    /**
     * This method delets soda from fridge
     *
     * @param username
     * @param password
     * @return void
     * @throws FridgeManagementException
     */
    @PostMapping(value = "v1/fridge/{user}/{username, password}")
    public void authenticateUser(@PathVariable String username, @PathVariable String password) throws FridgeManagementException {
        userService.authenticateUser(username, password);
    }

}

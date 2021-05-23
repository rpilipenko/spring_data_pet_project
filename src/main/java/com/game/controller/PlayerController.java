package com.game.controller;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/")
public class PlayerController {

    Logger log = Logger.getLogger(PlayerController.class.getName());

    @Autowired
    private PlayerService playerService;

    @RequestMapping(
            value = "/rest/players",
            method = RequestMethod.GET)
    public List<Player> getPlayersList(@RequestParam(required = false) String name,
                                       @RequestParam(required = false) String title,
                                       @RequestParam(required = false) Race race,
                                       @RequestParam(required = false) Profession profession,
                                       @RequestParam(required = false) Long after,
                                       @RequestParam(required = false) Long before,
                                       @RequestParam(required = false) Boolean banned,
                                       @RequestParam(required = false) Integer minExperience,
                                       @RequestParam(required = false) Integer maxExperience,
                                       @RequestParam(required = false) Integer minLevel,
                                       @RequestParam(required = false) Integer maxLevel,
                                       @RequestParam(required = false) PlayerOrder order,
                                       @RequestParam(required = false) Integer pageNumber,
                                       @RequestParam(required = false) Integer pageSize) {
        return playerService.getPlayerList(name,
                title,
                race,
                profession,
                after,
                before,
                banned,
                minExperience,
                maxExperience,
                minLevel,
                maxLevel,
                order,
                pageNumber,
                pageSize);
    }

    @RequestMapping(
            value = "/rest/players/count",
            method = RequestMethod.GET)
    public int getPlayersCount(@RequestParam(required = false) String name,
                               @RequestParam(required = false) String title,
                               @RequestParam(required = false) Race race,
                               @RequestParam(required = false) Profession profession,
                               @RequestParam(required = false) Long after,
                               @RequestParam(required = false) Long before,
                               @RequestParam(required = false) Boolean banned,
                               @RequestParam(required = false) Integer minExperience,
                               @RequestParam(required = false) Integer maxExperience,
                               @RequestParam(required = false) Integer minLevel,
                               @RequestParam(required = false) Integer maxLevel) {
        return playerService.getPlayersCount(name,
                title,
                race,
                profession,
                after,
                before,
                banned,
                minExperience,
                maxExperience,
                minLevel,
                maxLevel);
    }

    @RequestMapping(
            value = "/rest/players",
            method = RequestMethod.POST)
    public Player createPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    @RequestMapping(
            value = "/rest/players/{id}",
            method = RequestMethod.GET)
    public Player getPlayer(@PathVariable Long id) {
        return playerService.getPlayer(id);
    }

    @RequestMapping(
            value = "/rest/players/{id}",
            method = RequestMethod.POST)
    public Player updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        return playerService.updatePlayer(id, player);
    }

    @RequestMapping(
            value = "/rest/players/{id}",
            method = RequestMethod.DELETE)
    public void deletePlayer(@PathVariable Long id) {
        playerService.deleteById(id);
    }


}

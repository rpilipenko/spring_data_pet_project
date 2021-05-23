package com.game.service;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.exceptions.PlayerNotFoundException;
import com.game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PlayerService {
    Logger log = Logger.getLogger(PlayerService.class.getName());

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    PlayerValidationService playerValidationService;

    public Player createPlayer(Player player) {
        playerValidationService.validateParamsOfCreation(player);
        Player newPlayer = new Player(null,
                player.getName(),
                player.getTitle(),
                player.getProfession(),
                player.getRace(),
                player.getExperience(),
                null,
                null,
                player.getBirthday(),
                player.getBanned());
        return playerRepository.save(newPlayer);
    }


    public List<Player> getPlayerList(String name,
                                      String title,
                                      Race race,
                                      Profession profession,
                                      Long after,
                                      Long before,
                                      Boolean banned,
                                      Integer minExperience,
                                      Integer maxExperience,
                                      Integer minLevel,
                                      Integer maxLevel,
                                      PlayerOrder order,
                                      Integer pageNumber,
                                      Integer pageSize) {
        Pageable pageable = PageRequest.of(Optional.ofNullable(pageNumber).orElse(0),
                Optional.ofNullable(pageSize).orElse(3),
                Sort.by(Optional.ofNullable(order).orElse(PlayerOrder.ID).getFieldName()));
        return playerRepository.getPlayers(name, title, race, profession,
                Optional.ofNullable(after).map(Date::new).orElse(null),
                Optional.ofNullable(before).map(Date::new).orElse(null),
                banned, minExperience, maxExperience, minLevel, maxLevel, pageable).getContent();
    }

    public int getPlayersCount(String name,
                               String title,
                               Race race,
                               Profession profession,
                               Long after,
                               Long before,
                               Boolean banned,
                               Integer minExperience,
                               Integer maxExperience,
                               Integer minLevel,
                               Integer maxLevel) {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        return (int) playerRepository.getPlayers(name, title, race, profession,
                Optional.ofNullable(after).map(Date::new).orElse(null),
                Optional.ofNullable(before).map(Date::new).orElse(null),
                banned, minExperience, maxExperience, minLevel, maxLevel, pageable).getTotalElements();
    }

    public Player getPlayer(Long id) {
        playerValidationService.validateId(id);
        Optional<Player> byId = playerRepository.findById(id);
        if (!byId.isPresent()) {
            throw new PlayerNotFoundException();
        }
        return byId.get();
    }

    public void deleteById(Long id) {
        playerValidationService.validateId(id);
        if (!playerRepository.existsById(id)) {
            throw new PlayerNotFoundException();
        }
        playerRepository.deleteById(id);
    }

    public Player updatePlayer(Long id, Player player) {
        Player editPlayer = getPlayer(id);

        if (player.getName() != null) {
            playerValidationService.validateName(player.getName());
            editPlayer.setName(player.getName());
        }

        if (player.getTitle() != null) {
            playerValidationService.validateTitle(player.getTitle());
            editPlayer.setTitle(player.getTitle());
        }

        if (player.getRace() != null) {
            playerValidationService.validateRace(player.getRace());
            editPlayer.setRace(player.getRace());
        }

        if (player.getProfession() != null) {
            playerValidationService.validateProfession(player.getProfession());
            editPlayer.setProfession(player.getProfession());
        }

        if (player.getBirthday() != null) {
            playerValidationService.validateBirthday(player.getBirthday());
            editPlayer.setBirthday(player.getBirthday());
        }

        if (player.getBanned() != null) {
            playerValidationService.validateBanned(player.getBanned());
            editPlayer.setBanned(player.getBanned());
        }

        if (player.getExperience() != null) {
            playerValidationService.validateExperience(player.getExperience());
            editPlayer.setExperience(player.getExperience());
        }
        return playerRepository.save(editPlayer);
    }
}


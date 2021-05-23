package com.game.repository.listeners;

import com.game.entity.Player;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class PlayerEntityListener {


    @PrePersist
    @PreUpdate
    public void updateExpAndLevel(Player player) {

        player.setLevel(player.getCurrentLevel());
        player.setUntilNextLevel(player.tillNextLevel());


    }

}

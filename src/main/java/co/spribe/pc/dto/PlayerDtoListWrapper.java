package co.spribe.pc.dto;

import java.util.List;

public class PlayerDtoListWrapper {
    private List<PlayerDto> players;

    public List<PlayerDto> getPlayers() {
        return players;
    }

    public PlayerDtoListWrapper setPlayers(List<PlayerDto> players) {
        this.players = players;
        return this;
    }
}

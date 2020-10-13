package it.vizzarro.torganizer.service.model;

public class MatchSO {

    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_FIRST_TEAM = "firstTeam";
    public static final String PROPERTY_MSECOND_TEAM = "secondTeam";

    private Long id;
    private Long firstTeamId;
    private Long secondTeamId;
    private Long roundId;

    public MatchSO(Long id, Long firstTeamId, Long secondTeamId, Long roundId) {
        this.id = id;
        this.firstTeamId = firstTeamId;
        this.secondTeamId = secondTeamId;
        this.roundId = roundId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFirstTeamId() {
        return firstTeamId;
    }

    public void setFirstTeamId(Long firstTeamId) {
        this.firstTeamId = firstTeamId;
    }

    public Long getSecondTeamId() {
        return secondTeamId;
    }

    public void setSecondTeamId(Long secondTeamId) {
        this.secondTeamId = secondTeamId;
    }

    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }
}

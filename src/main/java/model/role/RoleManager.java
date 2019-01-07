package model.role;

public class RoleManager {
    private Role player1;
    private Role player2;
    private Role currentPlayer;

    private static RoleManager ourInstance = new RoleManager();

    public static RoleManager getInstance() {
        return ourInstance;
    }

    private RoleManager() {
    }

    public void init(Role role1,Role role2){
        this.player1 = role1;
        this.player2 = role2;
        this.currentPlayer = player1;
        this.player1.setOpponent(this.player2);
        this.player2.setOpponent(this.player1);
    }

    public Role getCurrentPlayer(){
        return this.currentPlayer;
    }

    public void nextPlayerRound(){
        this.currentPlayer = this.currentPlayer.getOpponent();
        this.currentPlayer.generateNextStep();
    }
}

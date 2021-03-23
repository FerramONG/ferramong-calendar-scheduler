package ferramong.scheduler.exceptions;

public class UnauthorizedException extends Exception {

    public UnauthorizedException() {
        super("Unauthorized access");
    }
}

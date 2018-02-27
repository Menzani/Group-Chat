package it.menzani.groupchat.client.view.connectionDialog;

public final class Result {
    private static final RuntimeException INSTANCE_NOT_ERROR =
            new IllegalStateException("This instance does not represent an error. Please check with #isSuccess() before calling this method.");

    private final String errorMessage;
    private final Component errorTarget;

    private Result(String errorMessage, Component errorTarget) {
        this.errorMessage = errorMessage;
        this.errorTarget = errorTarget;
    }

    public static Result success() {
        return new Result(null, null);
    }

    public static Result error(String errorMessage, Component errorTarget) {
        if (errorMessage == null || errorTarget == null) {
            throw new IllegalArgumentException("Please provide non-null message and target for error.");
        }
        return new Result(errorMessage, errorTarget);
    }

    boolean isSuccess() {
        return errorMessage == null;
    }

    String getErrorMessage() {
        if (isSuccess()) throw INSTANCE_NOT_ERROR;
        return errorMessage;
    }

    Component getErrorTarget() {
        if (isSuccess()) throw INSTANCE_NOT_ERROR;
        return errorTarget;
    }
}

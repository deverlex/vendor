package vn.needy.vendor.port.error;

import io.reactivex.functions.Consumer;

/**
 * Created by lion on 24/09/2017.
 */

public abstract class SafetyError implements Consumer<Throwable> {

    /**
     * Don't override this method.
     * Override {@link SafetyError#onSafetyError(BaseException)} instead
     */
    @Override
    public void accept(Throwable throwable) {
        if (throwable == null) {
            onSafetyError(BaseException.toUnexpectedError(new Throwable("Unknown exception")));
            return;
        }
        if (throwable instanceof BaseException) {
            onSafetyError((BaseException) throwable);
        } else {
            onSafetyError(BaseException.toUnexpectedError(throwable));
        }
    }

    public abstract void onSafetyError(BaseException error);
}


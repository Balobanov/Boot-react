import {CREDITS_REQUEST, CREDITS_SUCCESS, CREDITS_FAILED, CREDITS_UPDATE} from "./CreditsConstants";

export const creditsRequest = () => {
    return {
        type: CREDITS_REQUEST
    };
};

export const creditsSuccess = (credits) => {
    return {
        type: CREDITS_SUCCESS,
        credits
    };
};

export const creditsFailed = (errors) => {
    return {
        type: CREDITS_FAILED,
        errors
    };
};

export const creditsUpdate = (credit) => {
    return {
        type: CREDITS_UPDATE,
        credit
    };
};
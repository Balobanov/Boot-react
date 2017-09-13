import {CREDITS_REQUEST, CREDITS_SUCCESS, CREDITS_FAILED} from "../constants/CreditsConstants";

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
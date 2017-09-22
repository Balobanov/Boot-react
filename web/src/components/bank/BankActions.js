import {BANKS_REQUEST, BANKS_SUCCESS, BANKS_FAILED, BANKS_UPDATE} from "./BankConstants";

export const banksRequest = () => {
    return {
        type: BANKS_REQUEST
    };
};

export const banksSuccess = (banks) => {
    return {
        type: BANKS_SUCCESS,
        banks
    };
};

export const banksFailed = (errors) => {
    return {
        type: BANKS_FAILED,
        errors
    };
};

export const banksUpdate = (bank) => {
    return {
        type: BANKS_UPDATE,
        bank
    };
};
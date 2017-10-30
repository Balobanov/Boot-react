import {BANKS_REQUEST, BANKS_SUCCESS, BANKS_FAILED, BANKS_UPDATE, BANKS_EDIT} from "../constants/BankConstants";

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

export const banksUpdate = (id, name) => {
    return {
        type: BANKS_UPDATE,
        id,
        name
    };
};

export const banksEdit = (id) => {
    return {
        type: BANKS_EDIT,
        id
    };
};


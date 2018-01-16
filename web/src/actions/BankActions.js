import { BANKS_REQUEST, BANKS_SUCCESS, BANKS_FAILED, BANKS_UPDATE, BANKS_EDIT } from '../constants/BankConstants';

export const banksRequest = () => ({
    type: BANKS_REQUEST
});

export const banksSuccess = banks => ({
    type: BANKS_SUCCESS,
    banks
});

export const banksFailed = errors => ({
    type: BANKS_FAILED,
    errors
});

export const banksUpdate = (id, name) => ({
    type: BANKS_UPDATE,
    id,
    name
});

export const banksEdit = id => ({
    type: BANKS_EDIT,
    id
});


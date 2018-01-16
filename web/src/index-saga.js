import loginWatcher from './sagas/LoginSaga';
import signUpWatcher from './sagas/SignUpSaga';
import banksWatcher from './sagas/BankSaga';
import logoutWatcher from './sagas/LogoutSaga';
import facebookWatcher from './sagas/FacebookLoginSaga';

export default function* IndexSaga() {
    yield [
        loginWatcher(),
        signUpWatcher(),
        banksWatcher(),
        logoutWatcher(),
        facebookWatcher(),
    ];
}

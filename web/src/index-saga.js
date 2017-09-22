import loginWatcher from './components/login/LoginSaga';
import signUpWatcher from './components/signup/SignUpSaga';
import banksWatcher from './components/bank/BankSaga';
import logoutWatcher from './components/logout/LogoutSaga';

export default function* IndexSaga() {
    yield [
        loginWatcher(),
        signUpWatcher(),
        banksWatcher(),
        logoutWatcher()
    ];
}
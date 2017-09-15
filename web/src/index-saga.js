import loginWatcher from './components/login/LoginSaga';
import signUpWatcher from './components/signup/SignUpSaga';
import creditsWatcher from './components/credits/CreditsSaga';
import logoutWatcher from './components/logout/LogoutSaga';

export default function* IndexSaga() {
    yield [
        loginWatcher(),
        signUpWatcher(),
        creditsWatcher(),
        logoutWatcher()
    ];
}
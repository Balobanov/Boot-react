import loginWatcher from './components/login/saga/LoginSaga';
import signUpWatcher from './components/signup/saga/SignUpSaga';

export default function* IndexSaga() {
    yield [
        loginWatcher(),
        signUpWatcher()
    ];
}
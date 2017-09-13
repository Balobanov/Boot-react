import loginWatcher from './components/login/saga/LoginSaga';

export default function* IndexSaga() {
    yield [
        loginWatcher()
    ];
}
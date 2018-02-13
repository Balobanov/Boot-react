export const AUTH_KEY = 'auth';

export const saveAuthToLocalStore = (auth) => {
  localStorage.setItem(AUTH_KEY, JSON.stringify(auth));
};

export const getAuthFromLocalStore = () => {
  return JSON.parse(localStorage.getItem(AUTH_KEY));
};


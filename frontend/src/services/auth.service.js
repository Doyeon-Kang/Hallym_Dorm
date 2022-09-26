import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth/';

class AuthService {
  login(user) {
    return axios
      .post(API_URL + 'signin', {
        username: user.username,
        password: user.password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }
        console.log('login!');
        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
    console.log('logout!');
  }

  register(user) {
    return axios.post(API_URL + 'signup', {
      username: user.username,
      name: user.name,
      email: user.email,
      password: user.password
    });
  }
}

export default new AuthService();
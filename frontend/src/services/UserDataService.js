import http from "../http-common";

class UserDataService {
    getAll() {
        return http.get(`/users`)
    }
    delete(id) {
        return http.delete(`/users/${id}`);
    }
}
export default new UserDataService();
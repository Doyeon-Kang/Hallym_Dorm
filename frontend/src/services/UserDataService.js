import http from "../http-common";

class UserDataService {
    getAll() {
        return http.get(`/users`)
    }
}
export default new UserDataService();
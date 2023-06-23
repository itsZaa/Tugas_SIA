package Controller;

import java.util.ArrayList;
import Model.DosenHonorer;
import Model.DosenTetap;
import Model.Karyawan;
import Model.Doktor;
import Model.Magister;
import Model.Sarjana;
import Model.User;

/**
 *
 * @author 1122015 - Gregorius Reza
 */

public class UserController {

    private ArrayList<User> listUser;

    public UserController() {
    }

    public UserController(ArrayList<User> listUser) {
        this.listUser = listUser;
    }

    public String getUserData(String name) {
        User selectedUser = null;
        for (User user : listUser) {
            if (user.getNama().equalsIgnoreCase(name)) {
                selectedUser = user;
                break;
            }
        }

        String status = "";
        if (selectedUser instanceof Sarjana) {
            status = "Mahasiswa Sarjana";
        } else if (selectedUser instanceof Magister) {
            status = "Mahasiswa Magister";
        } else if (selectedUser instanceof Doktor) {
            status = "Mahasiswa Doktor";
        } else if (selectedUser instanceof DosenTetap) {
            status = "Dosen Tetap";
        } else if (selectedUser instanceof DosenHonorer) {
            status = "Dosen Honorer";
        } else if (selectedUser instanceof Karyawan) {
            status = "Karyawan";
        }

        if (selectedUser != null) {
            return "Status : " + status + "\n" + selectedUser.toString();
        }
        return "User not found.";

    }
}

package com.machine.monitor.api.domain.useracess;

import com.machine.monitor.api.domain.machine.Machine;
import com.machine.monitor.api.domain.machine.MachineService;
import com.machine.monitor.api.domain.user.User;
import com.machine.monitor.api.domain.user.UserService;
import io.swagger.model.UserAcessRequest;
import org.springframework.stereotype.Service;

@Service
public class UserAcessRequestService {

    private MachineService machineService;
    private UserService userService;

    public UserAcessRequestService(MachineService machineService, UserService userService){

        this.machineService = machineService;
        this.userService = userService;
    }

    public void requestUserAcess(UserAcessRequest userAcessRequest) {

        Machine machine = machineService.findMachineById(userAcessRequest.getMachineId());
        User user = userService.findUserByLogin(userAcessRequest.getUserLogin());

        if(userAcessRequest.getIsUserAdmin()){

            machine.removeAdminUser();
        }

        machine.addUser(user, userAcessRequest.getIsUserAdmin());
        machineService.saveMachine(machine);
    }
}

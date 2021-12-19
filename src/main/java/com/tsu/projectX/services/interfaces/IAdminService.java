package com.tsu.projectX.services.interfaces;

import java.util.UUID;

public interface IAdminService {

    boolean approveUser(UUID id);

    boolean rejectUser(UUID id);
}

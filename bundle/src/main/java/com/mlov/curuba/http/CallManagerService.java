package com.mlov.curuba.http;

import com.mlov.curuba.exceptions.UnauthorizedException;
import com.mlov.curuba.http.impl.CallManagerServiceImpl;

import java.io.IOException;

public interface CallManagerService {

    String getJson(String id, CallManagerServiceImpl.CallType callType) throws IOException, UnauthorizedException;
}

package com.mlov.curuba.http;

import com.mlov.curuba.http.impl.CallManagerServiceImpl;

public interface CallManagerService {

    String getJson(String id, CallManagerServiceImpl.CallType callType);
}

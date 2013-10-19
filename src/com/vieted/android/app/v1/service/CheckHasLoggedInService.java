package com.vieted.android.app.v1.service;

import com.nttuyen.android.base.service.AsyncService;
import com.nttuyen.android.base.service.ServiceException;
import com.vieted.android.app.v1.dto.LoggedInfo;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/7/13
 * Time: 9:13 AM
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class CheckHasLoggedInService extends AsyncService<Void, LoggedInfo> {

    @Override
    protected LoggedInfo execute(Void aVoid) throws ServiceException {
        LoggedInfo info =  new LoggedInfo();
        info.setUsername("nttuyen_it@yahoo.com");
        info.setPassword("adminpass");
        return info;
    }
}

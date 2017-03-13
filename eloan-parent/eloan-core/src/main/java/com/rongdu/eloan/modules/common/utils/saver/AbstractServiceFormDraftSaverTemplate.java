package com.rongdu.eloan.modules.common.utils.saver;

import com.rongdu.eloan.modules.common.utils.databean.BasicServiceDataBean;
import com.rongdu.eloan.modules.common.utils.parser.impl.DefaultClassTypeParser;

/**
 * Created by FHJ on 2015/5/13.
 * 模板方法模式
 */
public abstract class AbstractServiceFormDraftSaverTemplate implements ServiceFormDraftSaver {
    private DefaultClassTypeParser classTypeParser = new DefaultClassTypeParser();

    @Override
    public void saveDraft(String serviceVariables, LoginContext loginInfo) throws Exception {
        BasicServiceDataBean bean=beforeSave(serviceVariables, loginInfo);
        doSave(bean);
        afterSave(bean);
    }

    /**
     * 调用Service层方法做保存
     * @throws Exception
     */
    protected abstract void doSave(BasicServiceDataBean bean) throws Exception;

    private BasicServiceDataBean beforeSave(String serviceVariables, LoginContext loginInfo) {
        BasicServiceDataBean databean=parseDataBean(serviceVariables, classTypeParser);
        return initDataBean(databean,loginInfo);
    }

    protected void afterSave(BasicServiceDataBean bean) {
    }

    private BasicServiceDataBean initDataBean(BasicServiceDataBean bean,LoginContext loginInfo) {
        bean.setLoginUserId(loginInfo.getLoginUser().getId());
        bean.setUserName(loginInfo.getLoginUser().getUserName());
        bean.setLoginUserRoleId(loginInfo.getRole().getId());
        bean.setOfficeId(loginInfo.getLoginUser().getOfficeId());
        return bean;
    }

    /**
     * 把前台接收的解析成Service层友好的DataBean
     * @param serviceVariables
     * @param defaultClassTypeParser
     */
    protected abstract BasicServiceDataBean parseDataBean(String serviceVariables, DefaultClassTypeParser defaultClassTypeParser);
}

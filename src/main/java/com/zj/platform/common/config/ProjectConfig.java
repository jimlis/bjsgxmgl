package com.zj.platform.common.config;

import com.zj.platform.shiro.config.JWTConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 项目配置文件信息
 */
@Component
@PropertySource("classpath:application.properties")
public class ProjectConfig {
    /**
     * 项目名，末尾不带 "/"
     */
	@Autowired(required = false)
	@Value("${projectName}")
    private String projectName;
    /**
     * 项目根目录，末尾带 "/"
     */
	@Autowired(required = false)
	@Value("${projectRootURL}")
    private String projectRootURL;

    /**
     * 演示模式
     */
	@Autowired(required = false)
	@Value("${demoMode}")
    private boolean demoMode;
    /**
     * 调试模式
     */
	@Autowired(required = false)
	@Value("${devMode}")
    private boolean devMode;
	
	@Autowired(required = false)
    private JWTConfig jwt;

    public boolean isDemoMode() {
        return demoMode;
    }

    public void setDemoMode(boolean demoMode) {
        this.demoMode = demoMode;
    }

    public boolean isDevMode() {
        return devMode;
    }

    public void setDevMode(boolean devMode) {
        this.devMode = devMode;
    }

    public String getProjectRootURL() {
        return projectRootURL;
    }

    public void setProjectRootURL(String projectRootURL) {
        this.projectRootURL = projectRootURL;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    public JWTConfig getJwt() {
        return jwt;
    }

    public void setJwt(JWTConfig jwt) {
        this.jwt = jwt;
    }

    @Override
    public String toString() {
        return "ProjectConfig{" +
                "projectName='" + projectName + '\'' +
                ", projectRootURL='" + projectRootURL + '\'' +
                ", demoMode=" + demoMode +
                ", devMode=" + devMode +
                ", jwt=" + jwt +
                '}';
    }
}

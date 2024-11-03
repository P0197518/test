package cn.edu.sgu.www.easyui.util;

import cn.edu.sgu.www.easyui.EasyuiAdmin;
import cn.edu.sgu.www.easyui.annotation.AnonymityAccess;
import cn.edu.sgu.www.easyui.entity.Permission;
import cn.edu.sgu.www.easyui.enums.RequestMethod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 接口资源扫描工具类
 * @author heyunlin
 * @version 1.0
 */
@Component
public class ResourceScanner {

    /**
     * 服务名
     */
    @Value("${spring.application.name}")
    private String SERVICE_NAME;

    private static List<String> classPaths = new ArrayList<>();
    private static final List<Permission> resources = new ArrayList<>();

    /**
     * 扫描controller包下的目录，生成权限
     * @param basePackage controller包
     * @return List<Permission>
     * @throws ClassNotFoundException 类找不到时抛出异常
     */
    public List<Permission> scan(String basePackage) throws ClassNotFoundException {
        // 删除掉上一次的数据
        if (!resources.isEmpty()) {
            resources.clear();
        }
        if (!classPaths.isEmpty()) {
            classPaths.clear();
        }

        String classpath = Objects.requireNonNull(EasyuiAdmin.class.getResource("/")).getPath();
        String searchPath = classpath + basePackage.replace(".", "/");

        classpath = classpath.replaceFirst("/", "");
        classPaths = getClassPaths(new File(searchPath));

        for(String classPath : classPaths) {
            // 得到类的全限定名
            classPath = classPath.replace(classpath.replace("/", "\\")
                    .replaceFirst("\\\\", ""), "")
                    .replace("\\", ".")
                    .replace(".class", "");
            classpath = classPath.substring(classPath.indexOf(basePackage));

            // 通过反射获取类的信息
            Class<?> cls = Class.forName(classpath);

            // 获取标注在类上的@RequestMapping注解
            RequestMapping requestMapping = cls.getAnnotation(RequestMapping.class);

            // 构建父权限
            Permission parent = new Permission();
            // 控制器类上的路径
            String prefix = "";

            if(requestMapping != null) {
                // path或者value
                prefix = requestMapping.value().length > 0 ? requestMapping.value()[0] : requestMapping.path()[0];

                parent.setType(0);
                parent.setUrl(prefix);
                parent.setId(SERVICE_NAME + "_" + cls.getSimpleName());
                parent.setValue(prefix.substring(1) + ":*");

                // 设置name
                if (cls.isAnnotationPresent(Api.class)) {
                    Api api = cls.getAnnotation(Api.class);

                    if (api != null) {
                        // 类的接口文档@Api注解的tags属性值
                        String name = api.tags()[0];

                        parent.setName(name);
                    }
                }

                resources.add(parent);
            }

            Method[] methods = cls.getDeclaredMethods();

            for (Method method : methods) {
                getClassAnnotation(method, prefix, cls.getSimpleName(), parent.getId());
            }
        }

        return resources;
    }

    /**
     * 得到类上面的注解信息
     * @param method Method
     * @param prefix String 控制器类上@RequestMapping注解指定的路径
     * @param controllerName 控制器名称
     * @param parentId String 父级权限ID
     */
    public void getClassAnnotation(Method method, String prefix, String controllerName, String parentId) {
        // 构建子权限
        Permission permission = new Permission();
        String url = null;

        // 获取url
        if (method.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            url = prefix + (requestMapping.value().length > 0 ? requestMapping.value()[0] : requestMapping.path()[0]);
            String requestMethod = requestMapping.method().length > 0 ? requestMapping.method()[0].name() : "get";

            permission.setMethod(RequestMethod.getValueByName(requestMethod));
        } else if (method.isAnnotationPresent(GetMapping.class)) {
            GetMapping getMapping = method.getAnnotation(GetMapping.class);
            url = prefix + getMapping.value()[0];

            permission.setMethod(RequestMethod.GET.getValue());
        } else if (method.isAnnotationPresent(PostMapping.class)) {
            PostMapping postMapping = method.getAnnotation(PostMapping.class);
            url = prefix + postMapping.value()[0];

            permission.setMethod(RequestMethod.POST.getValue());
        }

        // 处理URL
        if(url != null) {
            if (url.endsWith("/")) {
                url = url.substring(0, url.length() - 1);
            }

            permission.setUrl(url);
            permission.setValue(url.substring(1).replace("/", ":"));
        }

        // 设置value
        if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation operation = method.getAnnotation(ApiOperation.class);

            if (operation != null) {
                String name = operation.value();

                permission.setName(name);
            }
        }
        // 默认值0
        permission.setAnonymity(0);

        if (method.isAnnotationPresent(AnonymityAccess.class)) {
            AnonymityAccess annotation = method.getAnnotation(AnonymityAccess.class);

            if (annotation != null) {
                permission.setAnonymity(annotation.value() ? 1 : 0);
            }
        }

        permission.setType(1);
        permission.setParentId(parentId);
        permission.setId(SERVICE_NAME + "_" + controllerName + "_" + method.getName());

        resources.add(permission);
    }

    private List<String> getClassPaths(File path) {
        if (path.isDirectory()) {
            File[] files = path.listFiles();

            if (files != null) {
                for (File file : files) {
                    getClassPaths(file);
                }
            }
        } else {
            if (path.getName().endsWith(".class")) {
                classPaths.add(path.getPath());
            }
        }

        return classPaths;
    }

}
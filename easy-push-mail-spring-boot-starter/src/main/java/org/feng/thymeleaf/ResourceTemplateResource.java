package org.feng.thymeleaf;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.thymeleaf.templateresource.ITemplateResource;
import org.thymeleaf.util.Validate;

import java.io.*;

/**
 * 模板位置查找实现类
 *
 * @author feng
 */
public class ResourceTemplateResource implements ITemplateResource, Serializable {
    private final Resource resource;
    private final String characterEncoding;

    public ResourceTemplateResource(ApplicationContext applicationContext, String path, String characterEncoding) {
        Validate.notNull(applicationContext, "ApplicationContext cannot be null");
        Validate.notNull(path, "path cannot be null");
        Validate.notNull(characterEncoding, "characterEncoding cannot be null");
        this.resource = applicationContext.getResource("classpath:" + path);
        this.characterEncoding = characterEncoding;
    }

    public ResourceTemplateResource(Resource resource, String characterEncoding) {
        Validate.notNull(resource, "Resource cannot be null");
        this.resource = resource;
        this.characterEncoding = characterEncoding;
    }

    @Override
    public String getDescription() {
        return this.resource.getDescription();
    }

    @Override
    public String getBaseName() {
        return this.resource.getFilename();
    }

    @Override
    public boolean exists() {
        return this.resource.exists();
    }

    @Override
    public Reader reader() throws IOException {
        InputStream inputStream = this.resource.getInputStream();
        return new BufferedReader(new InputStreamReader(new BufferedInputStream(inputStream), this.characterEncoding));
    }

    @Override
    public ITemplateResource relative(String relativeLocation) {
        Resource relativeResource = null;
        try {
            relativeResource = this.resource.createRelative(relativeLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResourceTemplateResource(relativeResource, this.characterEncoding);
    }
}

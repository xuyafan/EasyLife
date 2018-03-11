package com.github.xuyafan.easylife.generate;

import com.github.xuyafan.latte.wechat.template.AppRegisterTemplate;
import com.github.xuyafan.latte_annotations.AppRegisterGenerator;

/**
 * author： xuyafan
 * description:
 */
@AppRegisterGenerator(
        packageName = "com.github.xuyafan.easylife",
        registerTemplete = AppRegisterTemplate.class
)
public interface AppRegister {
}

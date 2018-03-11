package com.github.xuyafan.easylife.generate;

import com.github.xuyafan.latte.wechat.template.WXPayEntryTemplate;
import com.github.xuyafan.latte_annotations.PayEntryGenerator;

/**
 * author： xuyafan
 * description:
 */
@PayEntryGenerator(
        packageName = "com.github.xuyafan.easylife",
        payEntryTemplete = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}

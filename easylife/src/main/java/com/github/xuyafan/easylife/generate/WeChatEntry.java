package com.github.xuyafan.easylife.generate;

import com.github.xuyafan.latte.wechat.template.WXEntryTemplate;
import com.github.xuyafan.latte_annotations.EntryGenerator;

/**
 * author： xuyafan
 * description:
 */

@EntryGenerator(
        packageName = "com.github.xuyafan.easylife",
        entryTemplete = WXEntryTemplate.class
)
public interface WeChatEntry {
}

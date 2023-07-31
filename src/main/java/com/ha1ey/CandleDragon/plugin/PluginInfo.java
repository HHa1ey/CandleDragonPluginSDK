package com.ha1ey.CandleDragon.plugin;

import java.util.List;

public interface PluginInfo {
    void setPluginName(String name);
    void setPluginVersion(String version);
    void setPluginAuthor(String author);
    void setVulName(String vulName);
    void setVulId(String vid);
    void setVulCategory(String category);
    void setVulProduct(String product);
    void setVulScope(String scope);
    void setDescription(String description);
    void setVulDisclosureTime(String disclosureTime);


    void addExploit(List<Exploit> list);
    void addPoc(Poc poc);

}

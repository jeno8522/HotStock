import json

name = dict()
name["keywords"] = ("keywordContent", "newsIds")
name["keywords_themes"] = ("keywordContent", "themeNames" )


def to_json(data, type="default") -> str:
    if type == "default":
        return json.dumps(data, ensure_ascii=False)
        
    key_name, value_name = name[type]
    
    res_list = []
    for key, value in data.items():
        res_list.append({key_name: key, value_name: value})

    return to_json(res_list)



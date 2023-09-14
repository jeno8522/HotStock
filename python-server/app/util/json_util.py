import json

name = dict()
name["keywords"] = ("keywordContent", "subCount")
name["keywords_themes"] = ("keywordContent", "themeNames")


def to_json(data, type="default") -> str:
    if type == "default":
        return json.dumps(data, ensure_ascii=False)

    key_name, value_name = name[type]

    res_list = []
    for key, value in data.items():
        res_list.append('{"%s":%s, "%s":%s}' %
                        (key_name, to_json(key), value_name, to_json(value)))

    return "[%s]" % (", ".join(res_list))

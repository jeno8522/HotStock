import { Keyword } from "@/types";

// interface KeywordProps {
//     keyword: Keyword;
// }

const KeywordDetail = ({
    params,
    keyword,
}: {
    params: { slug: string };
    keyword: Keyword;
}) => {
    const { name, id, themes } = keyword;
    return (
        <div>
            <div>{name}</div>
            <div>
                {themes.map((themeItem, index) => (
                    <div key={index}>{themeItem}</div>
                ))}
            </div>
        </div>
    );
};

export default KeywordDetail;

interface Article {
    title: string;
    content: string;
}

interface Keyword {
    name: string;
    theme: string[];
    article: Article[];
}

const dummy: Keyword = {
    name: "반도체",
    theme: ["업종1", "업종2", "업종3"],
    article: [
        {
            title: "기사 1 제목",
            content: "기사 1 내용",
        },
        {
            title: "기사 2 제목",
            content: "기사 2 내용",
        },
    ],
};
export default function KeywordDetail({
    params,
}: {
    params: { slug: string };
}) {
    return (
        <div>
            <div>{dummy.name}</div>
            <div>
                {dummy.theme.map((themeItem, index) => (
                    <div key={index}>{themeItem}</div>
                ))}
            </div>
        </div>
    );
}

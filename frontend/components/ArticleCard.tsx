import { Article } from "@/types";

interface ArticleProps {
    article: Article;
}

const ArticleCard = ({ article }: ArticleProps) => {
    const { title, content } = article;
    return (
        <div>
            <div>{title}</div>
            <div>{content}</div>
        </div>
    );
};

export default ArticleCard;

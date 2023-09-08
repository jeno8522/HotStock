import { Article } from "@/types";

interface ArticleProps {
    article: Article;
}

const ArticleCard = ({ article }: ArticleProps) => {
    const { title, date, content, company } = article;
    return (
        <div>
            <div>{title}</div>
            <div>{company}</div>
            <div>{date}</div>
            <div>{content}</div>
        </div>
    );
};

export default ArticleCard;

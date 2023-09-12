import { News } from "@/types";

interface ArticleProps {
  news: News;
}

const ArticleCard = ({ news }: ArticleProps) => {
  const { title, content } = news;
  return (
    <div>
      <div>{title}</div>
      <div>{content}</div>
    </div>
  );
};

export default ArticleCard;

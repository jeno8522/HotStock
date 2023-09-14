import { News } from "@/types";

interface ArticleProps {
  news: News;
}

const ArticleCard = ({ news }: ArticleProps) => {
  const { title, content } = news;
  return (
    <div>
      <div className="rounded-md my-3 border-2 border-gray-300 p-4">
        <div className="font-bold  pr-2">{title}</div>
        <div>{content}</div>
      </div>
    </div>
  );
};

export default ArticleCard;

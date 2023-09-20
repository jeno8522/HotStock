import { News } from "@/types";
import { findNewsCompany } from "@/utils";

interface ArticleProps {
  news: News;
}

const ArticleCard = ({ news }: ArticleProps) => {
  const { title, content, company, url } = news;
  const companyName = findNewsCompany(company);

  return (
    <div>
      <div className="rounded-md my-3 border-2 border-gray-300 p-4">
        <a target="_blank" href={url} className="font-bold pr-2">
          {title}
        </a>
        <div className="text-xs">{companyName}</div>
        <div>{content}</div>
      </div>
    </div>
  );
};

export default ArticleCard;

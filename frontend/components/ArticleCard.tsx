import { News } from "@/types";
import { findNewsCompany } from "@/utils";

interface ArticleProps {
  news: News;
}

const ArticleCard = ({ news }: ArticleProps) => {
  const { title, content, mediaCompanyNum, link } = news;
  const companyName = findNewsCompany(mediaCompanyNum);
  // console.log(news);

  const contentEmpty =
    !content || content.trim().length === 0 || content === "";

  return (
    <div>
      <div className="rounded-md my-3 border-2 border-gray-300 p-4">
        <a target="_blank" href={link} className="font-bold pr-2 truncate">
          {title}
        </a>
        <div className="text-xs">{companyName}</div>

        {!contentEmpty ? (
          <div>{content}</div>
        ) : (
          <div>
            <a
              target="_blank"
              href={link}
              className="pr-2 truncate hover:font-bold"
            >
              기사 원문을 확인해보세요
            </a>
          </div>
        )}
      </div>
    </div>
  );
};

export default ArticleCard;

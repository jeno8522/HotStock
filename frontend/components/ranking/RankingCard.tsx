import { KeywordProps } from "../../types/index";
import Link from "next/link";

interface getKeywordsProps {
    keyword: KeywordProps;
}

const RankingCard = ({ keyword }: getKeywordsProps) => {
    const { id, name } = keyword;
    return (
        <div>
            <Link
                href={`/keyword/${keyword.id}`}
                className="flex drop-shadow-xl text-xl"
            >
                <div className="text-black-500 m-2">{id}</div>
                <div className="text-black-500 m-2 font-bold truncate">
                    {name}
                </div>
            </Link>
        </div>
    );
};

export default RankingCard;

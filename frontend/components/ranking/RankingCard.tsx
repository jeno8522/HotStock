import { KeywordProps } from "../../types/index";
import Link from "next/link";

interface getKeywordsProps {
    keyword: KeywordProps;
}

const RankingCard = ({ keyword }: getKeywordsProps) => {
    const { id, name } = keyword;
    return (
        <div>
            <Link href={`/keyword/${keyword.id}`}>
                {id}, {name}
            </Link>
        </div>
    );
};

export default RankingCard;

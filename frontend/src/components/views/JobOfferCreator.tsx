import NewJobForm from "../forms/NewJobForm";
import style from "./JobOfferCreator.module.css";

const JobOfferCreator = () => {
  return (
    <section className={style.container}>
      <NewJobForm />
    </section>
  );
};

export default JobOfferCreator;

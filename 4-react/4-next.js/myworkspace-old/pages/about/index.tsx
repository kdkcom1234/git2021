import Layout from "../../components/layout";
import Sidebar from "../../components/about/sidebar";

export default function About() {
  return (
    <Layout>
      <article className="d-flex">
        <Sidebar />
        <section>About Page</section>
      </article>
    </Layout>
  );
}
